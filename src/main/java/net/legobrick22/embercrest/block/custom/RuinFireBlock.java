package net.legobrick22.embercrest.block.custom;

import com.mojang.serialization.MapCodec;
import it.crystalnest.prometheus.api.type.FireTyped;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.Map;
import java.util.function.Function;

import net.legobrick22.embercrest.Embercrest;
import net.legobrick22.embercrest.block.ModBlocks;
import net.legobrick22.embercrest.fire.FireTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Util;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.slf4j.LoggerFactory;

import static net.legobrick22.embercrest.fire.FireTypes.RUIN_FIRE_TYPE;

public class RuinFireBlock extends BaseFireBlock implements FireTyped {
    public static final MapCodec<net.legobrick22.embercrest.block.custom.RuinFireBlock> CODEC = simpleCodec(net.legobrick22.embercrest.block.custom.RuinFireBlock::new);
    public static final int MAX_AGE = 15;
    public static final IntegerProperty AGE;
    public static final BooleanProperty NORTH;
    public static final BooleanProperty EAST;
    public static final BooleanProperty SOUTH;
    public static final BooleanProperty WEST;
    public static final BooleanProperty UP;
    public static final Map<Direction, BooleanProperty> PROPERTY_BY_DIRECTION;
    private final Function<BlockState, VoxelShape> shapes;
    private static final int IGNITE_INSTANT = 60;
    private static final int IGNITE_EASY = 30;
    private static final int IGNITE_MEDIUM = 15;
    private static final int IGNITE_HARD = 5;
    private static final int BURN_INSTANT = 100;
    private static final int BURN_EASY = 60;
    private static final int BURN_MEDIUM = 20;
    private static final int BURN_HARD = 5;
    private final Object2IntMap<Block> igniteOdds = new Object2IntOpenHashMap();
    private final Object2IntMap<Block> burnOdds = new Object2IntOpenHashMap();

    public MapCodec<net.legobrick22.embercrest.block.custom.RuinFireBlock> codec() {
        return CODEC;
    }

    public RuinFireBlock(BlockBehaviour.Properties properties) {
        super(properties, 1.0F);
        this.registerDefaultState((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(AGE, 0)).setValue(NORTH, false)).setValue(EAST, false)).setValue(SOUTH, false)).setValue(WEST, false)).setValue(UP, false));
        this.shapes = this.makeShapes();
    }

    private Function<BlockState, VoxelShape> makeShapes() {
        Map<Direction, VoxelShape> shapes = Shapes.rotateAll(Block.boxZ((double)16.0F, (double)0.0F, (double)1.0F));
        return this.getShapeForEachState((state) -> {
            VoxelShape shape = Shapes.empty();

            for(Map.Entry<Direction, BooleanProperty> entry : PROPERTY_BY_DIRECTION.entrySet()) {
                if ((Boolean)state.getValue((Property)entry.getValue())) {
                    shape = Shapes.or(shape, (VoxelShape)shapes.get(entry.getKey()));
                }
            }

            return shape.isEmpty() ? SHAPE : shape;
        }, new Property[]{AGE});
    }

    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos, Direction directionToNeighbour, BlockPos neighbourPos, BlockState neighbourState, RandomSource random) {
        return this.canSurvive(state, level, pos) ? this.getStateWithAge(level, pos, (Integer)state.getValue(AGE)) : Blocks.AIR.defaultBlockState();
    }


    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return (VoxelShape)this.shapes.apply(state);
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.getStateForPlacement(context.getLevel(), context.getClickedPos());

    }


    protected BlockState getStateForPlacement(BlockGetter level, BlockPos pos) {
        BlockPos below = pos.below();
        BlockState belowState = level.getBlockState(below);
        if (!this.canCatchFire(level, below, Direction.UP) && !belowState.isFaceSturdy(level, below, Direction.UP)) {
            BlockState result = this.defaultBlockState();

            for(Direction direction : Direction.values()) {
                BooleanProperty property = (BooleanProperty)PROPERTY_BY_DIRECTION.get(direction);
                if (property != null) {
                    result = (BlockState)result.setValue(property, this.canCatchFire(level, pos.relative(direction), direction.getOpposite()));
                }
            }

            return result;
        } else {
            return this.defaultBlockState();
        }
    }
    public static BlockState getState(BlockGetter level, BlockPos pos) {
        BlockPos below = pos.below();
        BlockState belowState = level.getBlockState(below);
        return ((RuinFireBlock)ModBlocks.RUIN_FIRE.get()).getStateForPlacement(level, pos);
    }


    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos below = pos.below();
        return level.getBlockState(below).isFaceSturdy(level, below, Direction.UP) || this.isValidFireLocation(level, pos);
    }

    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        level.scheduleTick(pos, this, getFireTickDelay(level.getRandom()));
        LoggerFactory.getLogger(Embercrest.MOD_ID).error("{}", getFireType());

        if (level.canSpreadFireAround(pos)) {
            if (!state.canSurvive(level, pos)) {
                level.removeBlock(pos, false);
            }

            BlockState belowState = level.getBlockState(pos.below());
            boolean infiniBurn = belowState.isFireSource(level, pos.below(), Direction.UP);
            int age = (Integer)state.getValue(AGE);
            if (!infiniBurn && level.isRaining() && this.isNearRain(level, pos) && random.nextFloat() < 0.2F + (float)age * 0.03F) {
                level.removeBlock(pos, false);
            } else {
                int newAge = Math.min(15, age + random.nextInt(3) / 2);
                if (age != newAge) {
                    state = (BlockState)state.setValue(AGE, newAge);
                    level.setBlock(pos, state, 260);
                }

                if (!infiniBurn) {
                    if (!this.isValidFireLocation(level, pos)) {
                        BlockPos below = pos.below();
                        if (!level.getBlockState(below).isFaceSturdy(level, below, Direction.UP) || age > 3) {
                            level.removeBlock(pos, false);
                        }

                        return;
                    }

                    if (age == 15 && random.nextInt(4) == 0 && !this.canCatchFire(level, pos.below(), Direction.UP)) {
                        level.removeBlock(pos, false);
                        return;
                    }
                }

                boolean increasedBurnout = (Boolean)level.environmentAttributes().getValue(EnvironmentAttributes.INCREASED_FIRE_BURNOUT, pos);
                int extra = increasedBurnout ? -50 : 0;
                this.checkBurnOut(level, pos.east(), 300 + extra, random, age, Direction.WEST);
                this.checkBurnOut(level, pos.west(), 300 + extra, random, age, Direction.EAST);
                this.checkBurnOut(level, pos.below(), 250 + extra, random, age, Direction.UP);
                this.checkBurnOut(level, pos.above(), 250 + extra, random, age, Direction.DOWN);
                this.checkBurnOut(level, pos.north(), 300 + extra, random, age, Direction.SOUTH);
                this.checkBurnOut(level, pos.south(), 300 + extra, random, age, Direction.NORTH);
                BlockPos.MutableBlockPos testPos = new BlockPos.MutableBlockPos();

                for(int xx = -1; xx <= 1; ++xx) {
                    for(int zz = -1; zz <= 1; ++zz) {
                        for(int yy = -1; yy <= 4; ++yy) {
                            if (xx != 0 || yy != 0 || zz != 0) {
                                int rate = 100;
                                if (yy > 1) {
                                    rate += (yy - 1) * 100;
                                }

                                testPos.setWithOffset(pos, xx, yy, zz);
                                int igniteOdds = this.getIgniteOdds(level, testPos);
                                if (igniteOdds > 0) {
                                    int odds = (igniteOdds + 40 + level.getDifficulty().getId() * 7) / (age + 30);
                                    if (increasedBurnout) {
                                        odds /= 2;
                                    }

                                    if (odds > 0 && random.nextInt(rate) <= odds && (!level.isRaining() || !this.isNearRain(level, testPos))) {
                                        int spreadAge = Math.min(15, age + random.nextInt(5) / 4);
                                        level.setBlock(testPos, this.getStateWithAge(level, testPos, spreadAge), 3);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    protected boolean isNearRain(Level level, BlockPos testPos) {
        return level.isRainingAt(testPos) || level.isRainingAt(testPos.west()) || level.isRainingAt(testPos.east()) || level.isRainingAt(testPos.north()) || level.isRainingAt(testPos.south());
    }

    /** @deprecated */
    @Deprecated
    public int getBurnOdds(BlockState state) {
        return state.hasProperty(BlockStateProperties.WATERLOGGED) && (Boolean)state.getValue(BlockStateProperties.WATERLOGGED) ? 0 : this.burnOdds.getInt(state.getBlock());
    }

    /** @deprecated */
    @Deprecated
    public int getIgniteOdds(BlockState state) {
        return state.hasProperty(BlockStateProperties.WATERLOGGED) && (Boolean)state.getValue(BlockStateProperties.WATERLOGGED) ? 0 : this.igniteOdds.getInt(state.getBlock());
    }

    private void checkBurnOut(Level level, BlockPos pos, int chance, RandomSource random, int age, Direction face) {
        int odds = level.getBlockState(pos).getFlammability(level, pos, face);
        if (random.nextInt(chance) < odds) {
            BlockState oldState = level.getBlockState(pos);
            oldState.onCaughtFire(level, pos, face, (LivingEntity)null);
            if (random.nextInt(age + 10) < 5 && !level.isRainingAt(pos)) {
                int newAge = Math.min(age + random.nextInt(5) / 4, 15);
                level.setBlock(pos, this.getStateWithAge(level, pos, newAge), 3);
            } else {
                level.removeBlock(pos, false);
            }
        }

    }

    private BlockState getStateWithAge(LevelReader level, BlockPos pos, int age) {
        BlockState stateForPlacement = getState(level, pos);
        return stateForPlacement.is(ModBlocks.RUIN_FIRE) ? (BlockState)stateForPlacement.setValue(AGE, age) : stateForPlacement;
    }

    private boolean isValidFireLocation(BlockGetter level, BlockPos pos) {
        for(Direction direction : Direction.values()) {
            if (this.canCatchFire(level, pos.relative(direction), direction.getOpposite())) {
                return true;
            }
        }

        return false;
    }

    private int getIgniteOdds(LevelReader level, BlockPos pos) {
        if (!level.isEmptyBlock(pos)) {
            return 0;
        } else {
            int odds = 0;

            for(Direction direction : Direction.values()) {
                BlockState blockState = level.getBlockState(pos.relative(direction));
                odds = Math.max(blockState.getFireSpreadSpeed(level, pos.relative(direction), direction.getOpposite()), odds);
            }

            return odds;
        }
    }

    /** @deprecated */
    @Deprecated
    protected boolean canBurn(BlockState state) {
        return this.getIgniteOdds(state) > 0;
    }

    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        super.onPlace(state, level, pos, oldState, movedByPiston);
        level.scheduleTick(pos, this, getFireTickDelay(level.getRandom()));
    }

    private static int getFireTickDelay(RandomSource random) {
        return 30 + random.nextInt(10);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{AGE, NORTH, EAST, SOUTH, WEST, UP});
    }

    private void setFlammable(Block block, int igniteOdds, int burnOdds) {
        if (block == Blocks.AIR) {
            throw new IllegalArgumentException("Tried to set air on fire... This is bad.");
        } else {
            this.igniteOdds.put(block, igniteOdds);
            this.burnOdds.put(block, burnOdds);
        }
    }

    public boolean canCatchFire(BlockGetter world, BlockPos pos, Direction face) {
        return world.getBlockState(pos).isFlammable(world, pos, face);
    }

    static {
        AGE = BlockStateProperties.AGE_15;
        NORTH = PipeBlock.NORTH;
        EAST = PipeBlock.EAST;
        SOUTH = PipeBlock.SOUTH;
        WEST = PipeBlock.WEST;
        UP = PipeBlock.UP;
        PROPERTY_BY_DIRECTION = (Map)PipeBlock.PROPERTY_BY_DIRECTION.entrySet().stream().filter((e) -> e.getKey() != Direction.DOWN).collect(Util.toMap());
    }


    @Override
    public Identifier getFireType() {
        return RUIN_FIRE_TYPE;
    }
}
