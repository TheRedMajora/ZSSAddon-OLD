package addon.zeldaswordskills.handler;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import addon.zeldaswordskills.items.AddonItems;
import cpw.mods.fml.common.IWorldGenerator;

public class AddonOreGenerator implements IWorldGenerator{

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		switch(world.provider.dimensionId)
		{
		case -1:
		    generateNether(world, rand, chunkX * 16, chunkZ * 16);
		    break;
		case 0:
		    generateSurface(world, rand, chunkX * 16, chunkZ * 16);
		    break;
		case 1:
		    generateEnd(world, rand, chunkX * 16, chunkZ * 16);
		    break;
		}
	}

	private void generateEnd(World world, Random random, int i, int j) {}

	private void generateSurface(World world, Random random, int i, int j)
	{
		for(int k = 0; k < 10; k++)
		{
			int firstBlockXCoord = i + random.nextInt(16);
			int firstBlockYCoord = random.nextInt(50);
			int firstBlockZCoord = j + random.nextInt(16);
			
			(new WorldGenMinable(AddonItems.metal1ore, 1)).generate(world, random, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
			(new WorldGenMinable(AddonItems.metal2ore, 1)).generate(world, random, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
			(new WorldGenMinable(AddonItems.metal3ore, 1)).generate(world, random, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
		}
	}

	private void generateNether(World world, Random random, int i, int j) {}
}
