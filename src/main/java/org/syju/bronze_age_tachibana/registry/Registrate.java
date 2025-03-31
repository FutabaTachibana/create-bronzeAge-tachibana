package org.syju.bronze_age_tachibana.registry;

import com.simibubi.create.foundation.data.CreateRegistrate;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import org.syju.bronze_age_tachibana.BronzeAgeTachibana;

public class Registrate {
    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(BronzeAgeTachibana.MODID)
            .defaultCreativeTab((ResourceKey<CreativeModeTab>) null);

}
