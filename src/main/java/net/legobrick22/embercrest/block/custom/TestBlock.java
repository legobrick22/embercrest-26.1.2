package net.legobrick22.embercrest.block.custom;

import net.legobrick22.embercrest.item.ModItems;
import net.legobrick22.embercrest.tags.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.phys.BlockHitResult;

public class TestBlock extends Block {
    public TestBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState onState, Entity entity) {
        super.stepOn(level, pos, onState, entity);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (itemStack.is(ModTags.Items.TEST_INTERACT)) {

            level.addParticle(ParticleTypes.END_ROD, pos.getX() + 0.5, pos.getY() + 1, pos.getZ(), 0, 1, 0);
            level.playSound(player, pos, SoundEvents.LIGHTNING_BOLT_IMPACT, SoundSource.BLOCKS, 2f, 2f);
            player.sendSystemMessage(Component.translatable("block.embercrest.test_block.interact"));

            Item item = itemStack.getItem();
            player.awardStat(Stats.ITEM_USED.get(item));
        }
        else { if (itemStack.isEmpty()){
            level.addParticle(ParticleTypes.FLAME, pos.getX() + 0.5, pos.getY() + 1, pos.getZ(), 0, 1, 0);
            level.playSound(player, pos, SoundEvents.AMETHYST_BLOCK_RESONATE, SoundSource.BLOCKS, 2f, 2f);

        } else {
            level.addParticle(ParticleTypes.SCULK_SOUL, pos.getX() + 0.5, pos.getY() + 1, pos.getZ(), 0, 1, 0);
            level.playSound(player, pos, SoundEvents.VILLAGER_HURT, SoundSource.BLOCKS, 2f, 2f);
        }
        }

        return InteractionResult.SUCCESS;

        }
    private boolean isValidItem(ItemStack item) {
        return item.is(ModTags.Items.TEST_INTERACT);
}
        }
