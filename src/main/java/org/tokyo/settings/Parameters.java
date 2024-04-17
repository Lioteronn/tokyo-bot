package org.tokyo.settings;

public class Parameters {

    private static boolean adapterOn = false;
    private static String adapterChannelId = "";

    public static String getAdapterChannelId() {
        return adapterChannelId;
    }

    public static void setAdapterChannelId(String adapterChannelId) {
        Parameters.adapterChannelId = adapterChannelId;
    }
    public static boolean isAdapterOn() {
        return adapterOn;
    }

    public static void setAdapterOn(boolean isAdapterOn) {
        Parameters.adapterOn = isAdapterOn;
    }
}
