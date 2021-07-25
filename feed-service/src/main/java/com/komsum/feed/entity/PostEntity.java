package com.komsum.feed.entity;

import java.time.Instant;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Table("POST")
@Data
@NoArgsConstructor
public class PostEntity {

	@PrimaryKeyColumn(name = "CREATED_AT", ordinal = 0, ordering = Ordering.DESCENDING)
    private Instant createdAt;

    @PrimaryKeyColumn(name = "POST_ID", ordinal = 1)
    private String postId;
    
    @PrimaryKeyColumn(name = "STREET_ID", ordinal = 2, type = PrimaryKeyType.PARTITIONED)
    private Integer streetId;
    
    @PrimaryKeyColumn(name = "TAG_ID", ordinal = 3)
    private String tagId;
    
}
