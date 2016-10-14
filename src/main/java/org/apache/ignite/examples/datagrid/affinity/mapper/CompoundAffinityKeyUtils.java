package org.apache.ignite.examples.datagrid.affinity.mapper;

public final class CompoundAffinityKeyUtils {
    public static final char AFFINITY_KEY_SEP = '|';

    /** Utility class. */
    private CompoundAffinityKeyUtils() {}

    public static String createCompoundAffinityKey(String affinityKey, String entityKey) {
        if (affinityKey == null)
            throw new NullPointerException("Affinity Key cannot be null!");

        return entityKey == null ? affinityKey : affinityKey + AFFINITY_KEY_SEP + entityKey;
    }

    public static String getAffinityKeyFromCompound(String key) {
        if (key == null)
            throw new NullPointerException("Key cannot be null!");

        int affinityKeyEnd = key.indexOf(CompoundAffinityKeyUtils.AFFINITY_KEY_SEP);

        if (affinityKeyEnd == 0)
            throw new IllegalArgumentException("Affinity Key is empty!");

        return affinityKeyEnd > 0 ? key.substring(0, affinityKeyEnd) : key;
    }
}
