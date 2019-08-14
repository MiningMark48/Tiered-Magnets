package com.miningmark48.tieredmagnets.block;

import com.miningmark48.tieredmagnets.container.ContainerMagneticInsulator;
import com.miningmark48.tieredmagnets.init.ModBlocks;
import com.miningmark48.tieredmagnets.tileentity.TileEntityMagneticInsulator;
import com.miningmark48.tieredmagnets.util.ModTranslate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class BlockMagneticInsulator extends ContainerBlock {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    private static final BooleanProperty POWERED = BooleanProperty.create("powered");

    private VoxelShape SHAPE_BASE = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 2.0D, 15.0D);
    private VoxelShape SHAPE_TOP = Block.makeCuboidShape(4.0D, 2D, 4.0D, 12.0D, 14.25D, 12.0D);

    public BlockMagneticInsulator(Properties properties) {
        super(properties);
        this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, Direction.NORTH).with(POWERED, false));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader world, List<ITextComponent> list, ITooltipFlag flag) {
        list.add(new StringTextComponent(TextFormatting.YELLOW + ModTranslate.toLocal("tooltip.block.magnetic_insulator.line1")));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(FACING);
        builder.add(POWERED);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return VoxelShapes.or(SHAPE_BASE, SHAPE_TOP);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader p_220071_2_, BlockPos p_220071_3_, ISelectionContext p_220071_4_) {

        return VoxelShapes.or(SHAPE_BASE, SHAPE_TOP);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new TileEntityMagneticInsulator(ModBlocks.ModTileEntities.MAGNETIC_INSULATOR);
    }

    @Override
    public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult ray) {
        if (!player.isSneaking()) {
            if (player instanceof ServerPlayerEntity) {
                TileEntity te = world.getTileEntity(pos);
                if (te instanceof TileEntityMagneticInsulator) {
                    NetworkHooks.openGui((ServerPlayerEntity) player, new INamedContainerProvider() {
                        @Nonnull
                        @Override
                        public ITextComponent getDisplayName() {
                            return state.getBlock().getNameTextComponent();
                        }

                        @Nonnull
                        @Override
                        public Container createMenu(int i, @Nonnull PlayerInventory playerInventory, @Nonnull PlayerEntity playerEntity) {
                            return new ContainerMagneticInsulator(i, playerInventory, (TileEntityMagneticInsulator) te);
                        }
                    }, packetBuffer -> packetBuffer.writeBlockPos(pos));
                }
            }
        }
        return true;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState state2, boolean p_220082_5_) {
        super.onBlockAdded(state, world, pos, state2, p_220082_5_);
        this.setDefaultFacing(world, pos, state);
        world.setBlockState(pos, state.with(POWERED, world.isBlockPowered(pos)));
    }

    @SuppressWarnings("Duplicates")
    private void setDefaultFacing(World worldIn, BlockPos pos, BlockState state)
    {
        if (!worldIn.isRemote) {
            BlockState iblockstate = worldIn.getBlockState(pos.north());
            BlockState iblockstate1 = worldIn.getBlockState(pos.south());
            BlockState iblockstate2 = worldIn.getBlockState(pos.west());
            BlockState iblockstate3 = worldIn.getBlockState(pos.east());
            Direction enumfacing = state.get(FACING);
            if (enumfacing == Direction.NORTH && iblockstate.isSolid() && !iblockstate1.isSolid()) {
                enumfacing = Direction.SOUTH;
            } else if (enumfacing == Direction.SOUTH && iblockstate1.isSolid() && !iblockstate.isSolid()) {
                enumfacing = Direction.NORTH;
            } else if (enumfacing == Direction.WEST && iblockstate2.isSolid() && !iblockstate3.isSolid()) {
                enumfacing = Direction.EAST;
            } else if (enumfacing == Direction.EAST && iblockstate3.isSolid() && !iblockstate2.isSolid()) {
                enumfacing = Direction.WEST;
            }

            worldIn.setBlockState(pos, state.with(FACING, enumfacing), 2);
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlacementHorizontalFacing().getOpposite());
    }

    @SuppressWarnings("Duplicates")
    public void setState(World worldIn, BlockPos pos, boolean isActive)
    {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        worldIn.setBlockState(pos, worldIn.getBlockState(pos).with(POWERED, isActive), 3);

        if (tileentity != null)
        {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }

    @Override
    public BlockRenderType getRenderType(BlockState state)
    {
        return BlockRenderType.MODEL;
    }

    @Override
    public boolean isSolid(BlockState state){
        return false;
    }

    @Override
    public BlockRenderLayer getRenderLayer(){
        return BlockRenderLayer.CUTOUT;
    }

}
