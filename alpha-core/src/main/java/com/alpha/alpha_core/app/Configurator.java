package com.alpha.alpha_core.app;

import java.util.WeakHashMap;

public class Configurator {
    private static final WeakHashMap<String, Object> ALPHA_CONFIGS = new WeakHashMap<>();

    private Configurator() {
        ALPHA_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }


    //线程安全的懒汉模式（静态内部类）
    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    final WeakHashMap<String, Object> getAlphaConfigs() {
        return ALPHA_CONFIGS;
    }

    public final void configure() {
        ALPHA_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    public final Configurator withApiHost(String host) {
        ALPHA_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    private void checkConfiguration() {
        final boolean isReady = (boolean) ALPHA_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready , call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key) {
        checkConfiguration();
        return (T) ALPHA_CONFIGS.get(key.name());
    }
}
