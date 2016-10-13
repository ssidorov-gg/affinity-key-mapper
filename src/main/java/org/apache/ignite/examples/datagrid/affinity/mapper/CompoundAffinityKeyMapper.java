package org.apache.ignite.examples.datagrid.affinity.mapper;

import org.apache.ignite.cache.affinity.AffinityKeyMapper;
import org.apache.ignite.internal.util.GridArgumentCheck;


/**
 * Affinity key mapper.
 */
public class CompoundAffinityKeyMapper implements AffinityKeyMapper {
    /** Serial version uid. */
    private static final long serialVersionUID = -4124381318718019236L;

    /**
     * {@inheritDoc}
     */
    @Override
    public Object affinityKey(Object key) {
        GridArgumentCheck.notNull(key, "key");

        String strKey = key.toString();

        return CompoundAffinityKeyUtils.getAffinityKeyFromCompound(strKey);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        // No-op.
    }
}
