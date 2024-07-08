package ru.Max.tgBot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import ru.Max.tgBot.DAO.CategoryRepository;
import ru.Max.tgBot.DAO.ProductRepository;
import ru.Max.tgBot.Entities.Category;
import ru.Max.tgBot.Entities.Product;

import java.lang.reflect.Array;
import java.util.Random;
import java.util.UUID;

@SpringBootTest
class FillingTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void createTestCategories(){

        final String[] ROLLS_CATEGORY_NAMES = {"Классические роллы", "Запечённые роллы", "Сладкие роллы", "Наборы"};
        final String[] BURGERS_CATEGORY_NAMES = {"Классические бургеры", "Острые бургеры"};
        final String[] DRINKS_CATEGORY_NAMES = {"Газированные напитки", "Энергетические напитки", "Соки", "Другие"};


        var pizzaCategory = createSubCategory("Пицца", null);
        for(int j = 0; j < 3; j++){
            createProduct(
                    pizzaCategory,
                    UUID.randomUUID().toString(),
                    UUID.randomUUID().toString(),
                    new Random().nextDouble()
            );
        }


        createCategory("Роллы", ROLLS_CATEGORY_NAMES);
        createCategory("Бургеры", BURGERS_CATEGORY_NAMES);
        createCategory("Напитки", DRINKS_CATEGORY_NAMES);

        ///
        Assertions.assertEquals(14, categoryRepository.findAll().size());
        Assertions.assertEquals(33, productRepository.findAll().size());
    }

    void createCategory(String categoryName, String[] names){
        Category categoryId = createSubCategory(categoryName, null);

        for(int i = 0; i < Array.getLength(names); i++){
            Category subCategory = createSubCategory(names[i], categoryId);
            for(int j = 0; j < 3; j++){
                createProduct(
                        subCategory,
                        UUID.randomUUID().toString(),
                        UUID.randomUUID().toString(),
                        new Random().nextDouble()
                );
            }
        }
    }

    Category createSubCategory(String categoryName, Category parent){
        Category category = new Category();
        category.setName(categoryName);
        category.setParent(parent);
        return categoryRepository.save(category);
    }

    void createProduct(Category category, String description, String name, Double price){
        Product product = new Product();
        product.setCategory(category);
        product.setDescription(description);
        product.setName(name);
        product.setPrice(price);
        productRepository.save(product);
    }

}
