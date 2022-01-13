package com.komsum.feed.entity;

import java.time.Instant;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Table("POST_USER")
@Data
@NoArgsConstructor
public class PostUserEntity {

	@PrimaryKeyColumn(name = "USERNAME", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String username;
	
	@PrimaryKeyColumn(name = "POST_ID", ordinal = 1)
    private String postId;
    
    @PrimaryKeyColumn(name = "STREET_ID", ordinal = 2)
    private Integer streetId;
    
    @PrimaryKeyColumn(name = "TAG_ID", ordinal = 3)
    private String tagId;
	
	@PrimaryKeyColumn(name = "CREATED_AT", ordinal = 4, ordering = Ordering.DESCENDING)
    private Instant createdAt;
	
	
}
