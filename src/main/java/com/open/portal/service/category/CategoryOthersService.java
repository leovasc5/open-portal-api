package com.open.portal.service.category;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.open.portal.domain.category.CategoryOthers;
import com.open.portal.repository.CategoryOthersRepository;

@Service
public class CategoryOthersService {
    @Autowired
    private CategoryOthersRepository categoryOthersRepository;

    public CategoryOthers create(CategoryOthers categoryOthers) {
        CategoryOthers categoryAlreadyCreated = categoryOthersRepository.findByName(categoryOthers.getName()).orElse(null);

        if (Objects.nonNull(categoryAlreadyCreated)) {
            return categoryAlreadyCreated;
        }

        return categoryOthersRepository.save(categoryOthers);
    }
}
