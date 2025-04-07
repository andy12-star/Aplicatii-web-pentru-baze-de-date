package com.andy.master.service.impl;

import com.andy.master.model.dto.request.TagRequest;
import com.andy.master.model.dto.response.TagResponse;
import com.andy.master.model.entity.Tag;
import com.andy.master.repository.TagRepository;
import com.andy.master.service.api.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public List<TagResponse> getAllTags() {
        return tagRepository.findAll().stream()
                .map(tag -> TagResponse.builder()
                        .id(tag.getId())
                        .name(tag.getName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public TagResponse getTagById(Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
        return TagResponse.builder().id(tag.getId()).name(tag.getName()).build();

    }

    @Override
    public TagResponse createTag(TagRequest tagRequest) {
        Tag tag = Tag.builder().name(tagRequest.getName()).build();
        return TagResponse.builder()
                .id(tagRepository.save(tag).getId())
                .name(tag.getName())
                .build();
    }

    @Override
    public TagResponse updateTag(Long id, TagRequest tagRequest) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
        tag.setName(tagRequest.getName());
        return TagResponse.builder()
                .id(tagRepository.save(tag).getId())
                .name(tag.getName())
                .build();
    }

    @Override
    public void deleteTag(Long id) {
    tagRepository.deleteById(id);
    }
}
