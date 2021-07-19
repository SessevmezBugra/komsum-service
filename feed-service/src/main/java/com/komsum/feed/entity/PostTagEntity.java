package com.komsum.feed.entity;

import java.time.Instant;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Table("POST_TAG")
@Data
@NoArgsConstructor
public class PostTagEntity {

	@PrimaryKeyColumn(name = "TAG_ID", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String tagId;

    @PrimaryKeyColumn(name = "CREATED_AT", ordinal = 1, ordering = Ordering.DESCENDING)
    private Instant createdAt;

    @PrimaryKeyColumn(name = "POST_ID", ordinal = 2)
    private String postId;
    
    @Column("STREET_ID")
    private Integer streetId;
}
