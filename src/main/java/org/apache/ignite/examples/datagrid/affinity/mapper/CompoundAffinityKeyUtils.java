package org.apache.ignite.examples.datagrid.affinity.mapper;

import org.apache.ignite.internal.util.GridArgumentCheck;

public final class CompoundAffinityKeyUtils {
    public static final char AFFINITY_KEY_SEP = '|';

    /** Utility class. */
    private CompoundAffinityKeyUtils() {}

    public static String createCompoundAffinityKey(String affinityKey, String entityKey) {
        GridArgumentCheck.notNull(affinityKey, "affinity key");

        return entityKey == null ? affinityKey : affinityKey + AFFINITY_KEY_SEP + entityKey;
    }

    public static String getAffinityKeyFromCompound(String key) {
        GridArgumentCheck.notNull(key, "key");

        int affinityKeyEnd = key.indexOf(CompoundAffinityKeyUtils.AFFINITY_KEY_SEP);

        return affinityKeyEnd > 0 ? key.substring(0, affinityKeyEnd) : key;
    }
}
