package de.melanx.MoreVanillaArmor.data;

import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.data.TagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;

public class ModTags {

    public static class Blocks {
        public static final ITag.INamedTag<Block> STORAGE_BLOCKS_GLOWSTONE = tag("storage_blocks/glowstone");
        public static final ITag.INamedTag<Block> MAGMA_BLOCK = tag("magma_block");
        public static final ITag.INamedTag<Block> NETHER_BRICKS = tag("netherbricks");
        public static final ITag.INamedTag<Block> PRISMARINE = tag("prismarine");
        public static final ITag.INamedTag<Block> SLIME_BLOCK = tag("slime_block");

        private static ITag.INamedTag<Block> tag(String name) {
            return net.minecraft.tags.BlockTags.makeWrapperTag("forge:" + name);
        }
    }

    public static class Items {
        public static final ITag.INamedTag<Item> STORAGE_BLOCKS_GLOWSTONE = tag("storage_blocks/glowstone");
        public static final ITag.INamedTag<Item> MAGMA_BLOCK = tag("magma_block");
        public static final ITag.INamedTag<Item> NETHER_BRICKS = tag("netherbricks");
        public static final ITag.INamedTag<Item> PRISMARINE = tag("prismarine");
        public static final ITag.INamedTag<Item> SLIME_BLOCK = tag("slime_block");

        public static final ITag.INamedTag<Item> PAPER = tag("paper");

        private static ITag.INamedTag<Item> tag(String name) {
            return net.minecraft.tags.ItemTags.makeWrapperTag("forge:" + name);
        }
    }

    public static class BlockTags extends BlockTagsProvider {
        public BlockTags(DataGenerator generator) {
            super(generator);
        }

        @Override
        protected void registerTags() {
            getOrCreateBuilder(Blocks.STORAGE_BLOCKS_GLOWSTONE).add(net.minecraft.block.Blocks.GLOWSTONE);
            getOrCreateBuilder(Blocks.MAGMA_BLOCK).add(net.minecraft.block.Blocks.MAGMA_BLOCK);
            getOrCreateBuilder(Blocks.NETHER_BRICKS).add(net.minecraft.block.Blocks.NETHER_BRICKS);
            getOrCreateBuilder(Blocks.PRISMARINE).add(net.minecraft.block.Blocks.PRISMARINE);
            getOrCreateBuilder(Blocks.SLIME_BLOCK).add(net.minecraft.block.Blocks.SLIME_BLOCK);
        }
    }

    public static class ItemTags extends ItemTagsProvider {
        public ItemTags(DataGenerator generator, BlockTagsProvider blockTags) {
            super(generator, blockTags);
        }

        @Override
        protected void registerTags() {
            getOrCreateBuilder(Items.PAPER).add(net.minecraft.item.Items.PAPER);

            copy(ModTags.Blocks.STORAGE_BLOCKS_GLOWSTONE, ModTags.Items.STORAGE_BLOCKS_GLOWSTONE);
            copy(Blocks.MAGMA_BLOCK, Items.MAGMA_BLOCK);
            copy(Blocks.NETHER_BRICKS, Items.NETHER_BRICKS);
            copy(Blocks.PRISMARINE, Items.PRISMARINE);
            copy(Blocks.SLIME_BLOCK, Items.SLIME_BLOCK);
        }
    }

}
