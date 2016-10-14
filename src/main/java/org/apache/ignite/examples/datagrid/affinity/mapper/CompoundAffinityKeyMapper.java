package org.apache.ignite.examples.datagrid.affinity.mapper;

import org.apache.ignite.cache.affinity.AffinityKeyMapper;


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
        if (key == null)
            throw new NullPointerException("Key cannot be null!");

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
