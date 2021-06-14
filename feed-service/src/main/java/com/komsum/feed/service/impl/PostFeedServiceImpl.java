package com.komsum.feed.service.impl;

import com.datastax.oss.driver.api.core.cql.PagingState;
import com.komsum.feed.dto.PostDto;
import com.komsum.feed.entity.PostFeedEntity;
import com.komsum.feed.exception.ResourceNotFoundException;
import com.komsum.feed.model.SlicedResult;
import com.komsum.feed.repository.PostFeedRepository;
import com.komsum.feed.service.PostFeedService;
import com.komsum.feed.util.constant.AppConstants;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.util.encoders.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostFeedServiceImpl implements PostFeedService {

    private final PostFeedRepository postFeedRepository;
    private final ModelMapper modelMapper;

    @Override
    public void create(PostDto postDto) {
        Instant currentDate = Instant.now();
        postDto.getTagIds().forEach(tagId -> {
            PostFeedEntity postFeedEntity = modelMapper.map(postDto, PostFeedEntity.class);
            postFeedEntity.setTagId(tagId);
            postFeedEntity.setCreatedAt(currentDate);
            postFeedRepository.save(postFeedEntity);
        });
    }

    @Override
    public PostFeedEntity update(PostFeedEntity postFeedEntity) {
        return postFeedRepository.save(postFeedEntity);
    }

    @Override
    public List<PostFeedEntity> findAll() {
        return postFeedRepository.findAll();
    }

    @Override
    public SlicedResult<PostFeedEntity> findByStreetIdAndTagIdInAndPage(Integer streetId, Iterable<Integer> tagIds, Optional<Integer> pageNumber) {
//        CassandraPageRequest request = pagingState
//                .map(p -> CassandraPageRequest
//                        .of(PageRequest.of(0, AppConstants.PAGE_SIZE), string2ByteBuffer(p, Charset.forName("UTF-8"))))
//                .orElse(CassandraPageRequest.first(AppConstants.PAGE_SIZE));
//        if (request.getPagingState() != null)
//            System.out.println(request.getPagingState().toString());
//
//        Slice<PostFeedEntity> page = postFeedRepository.findByStreetIdAndTagIdIn(streetId, tagIds, request);

//        if (page.isEmpty()) {
//            throw new ResourceNotFoundException(
//                    String.format("Feed not found"));
//        }
//
//        String pageState = null;
//
//        if (!page.isLast()) {
//            pageState = byteBuffer2String(((CassandraPageRequest) page.getPageable()).getPagingState(), Charset.forName("UTF-8"));
//        }
//        System.out.println(((CassandraPageRequest) page.getPageable()).getPagingState().toString());
//        return SlicedResult
//                .<PostFeedEntity>builder()
//                .content(page.getContent())
//                .isLast(page.isLast())
//                .pageState(pageState)
//                .build();

        Slice<PostFeedEntity> slice = postFeedRepository.findByStreetIdAndTagIdIn(streetId, tagIds, CassandraPageRequest.first(AppConstants.PAGE_SIZE));
        int currpage = 0;
        int desiredPage = pageNumber.map(p -> p).orElse(0);
        while(slice.hasNext() && currpage < desiredPage) {
            slice = postFeedRepository.findByStreetIdAndTagIdIn(streetId, tagIds, slice.nextPageable());
            currpage++;
        }
        return SlicedResult
                .<PostFeedEntity>builder()
                .content(slice.getContent())
                .isLast(slice.isLast())
                .build();
    }

    /**
     * Method Usage
     * ByteBuffer bf = string2ByteBuffer("test", Charset.forName("UTF-8"));
     **/
    public static ByteBuffer string2ByteBuffer(String s, Charset charset) {
        return ByteBuffer.wrap(s.getBytes(charset));
    }

    public static String byteBuffer2String(ByteBuffer buf, Charset charset) {
        byte[] bytes;
        if (buf.hasArray()) {
            bytes = buf.array();
        } else {
            buf.rewind();
            bytes = new byte[buf.remaining()];
        }
        return new String(bytes, charset);
    }

//    public PostFeedEntity convertTo(PostDto postDto)
}
