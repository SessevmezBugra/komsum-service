package com.komsum.feed.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.Instant;
import java.util.Date;

@Table("POST_FEED")
@Data
//@Builder
@NoArgsConstructor
public class PostFeedEntity {

    @PrimaryKeyColumn(name = "STREET_ID", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private Integer streetId;

    @PrimaryKeyColumn(name = "TAG_ID", ordinal = 1 )
    private Integer tagId;

    @PrimaryKeyColumn(name = "CREATED_AT", ordinal = 2, ordering = Ordering.DESCENDING)
    private Instant createdAt;

    @PrimaryKeyColumn(name = "POST_ID", ordinal = 3)
    private String postId;

}
