package com.komsum.feed.entity;

import java.time.Instant;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Table("POST_FILE")
@Data
@NoArgsConstructor
public class PostFileEntity {

	@PrimaryKeyColumn(name = "POST_ID", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String postId;
	
	@PrimaryKeyColumn(name = "FILE_ID", ordinal = 1 )
    private String fileId;
	
	@PrimaryKeyColumn(name = "CREATED_AT", ordinal = 2, ordering = Ordering.DESCENDING)
    private Instant createdAt;
}
