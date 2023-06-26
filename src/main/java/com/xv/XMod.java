package com.xv;

import com.xv.KeyF5andF6.KeyF5andF6;
import com.xv.WaterWalkingHorse.WaterWalkingHorse;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XMod implements ClientModInitializer, ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("xmod");
	public static final String MODID = "XMod";

	@Override
	public void onInitialize() {
		new KeyF5andF6();
		new WaterWalkingHorse();

		LOGGER.info("XMod加载完成！");
	}

	@Override
	public void onInitializeClient() {

	}
}