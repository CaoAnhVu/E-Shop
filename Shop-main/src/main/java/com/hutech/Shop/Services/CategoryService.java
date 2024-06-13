package com.hutech.Shop.Services;

import com.hutech.Shop.model.Catology;
import com.hutech.Shop.repository.CatologyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {
    private final CatologyRepository catologyRepository;
    public List<Catology> getAlCatologies(){
        return catologyRepository.findAll();
    }
    public Optional<Catology> getCategoryById(Long id) {
        return catologyRepository.findById(id);
    }
    public void addCategory(Catology category) {
        catologyRepository.saveAndFlush(category);
    }

    public void updateCategory(@NotNull Catology category) {
        Catology existingCategory = catologyRepository.findById((long) category.getId())
                .orElseThrow(() -> new IllegalStateException("Category with ID " + category.getId() + " does not exist."));
        existingCategory.setName(category.getName());
        existingCategory.setMeta(category.getMeta());
        existingCategory.setLink(category.getLink());
        existingCategory.setHide(category.isHide());
        existingCategory.setOrder(category.getOrder());
        catologyRepository.saveAndFlush(existingCategory);
    }

    public void deleteCategoryById(Long id) {
        if (!catologyRepository.existsById(id)) {
            throw new IllegalStateException("Category with ID " + id + " does not exist.");
        }
        catologyRepository.deleteById(id);
    }
}
