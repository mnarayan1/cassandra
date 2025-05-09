package org.apache.cassandra.cache;

import java.nio.ByteBuffer;
import java.util.Objects;
import org.apache.cassandra.db.filter.ColumnFilter;

public class DigestCacheKey {
    private final ByteBuffer partitionKey;
    private final String keyspace;
    private final String table;
    private final ColumnFilter columnFilter;
    private final int version;

    public DigestCacheKey(ByteBuffer partitionKey, String keyspace, String table, ColumnFilter columnFilter, int version) {
        this.partitionKey = partitionKey.duplicate();  // defensively copy
        this.keyspace = keyspace;
        this.table = table;
        this.columnFilter = columnFilter;
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DigestCacheKey)) return false;
        DigestCacheKey that = (DigestCacheKey) o;
        return version == that.version &&
               Objects.equals(keyspace, that.keyspace) &&
               Objects.equals(table, that.table) &&
               Objects.equals(columnFilter, that.columnFilter) &&
               partitionKey.equals(that.partitionKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partitionKey, keyspace, table, columnFilter, version);
    }
}