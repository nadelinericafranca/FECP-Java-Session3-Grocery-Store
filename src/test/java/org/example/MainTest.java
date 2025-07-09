package org.example;

import org.junit.jupiter.api.*;

import static org.example.Main.products;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @BeforeEach
    void setUp () {
        products.clear();
    }

    @Test
    void testAddProductWithValidQuantity() {
        String result = Main.addProduct("Banana", 30);
        assertEquals("Product Added!", result);
        assertTrue(products.containsKey("Banana"));
        assertEquals(30, products.get("Banana"));
    }

    @Test
    void testAddProductWithInvalidQuantity() {
        String result = Main.addProduct("Apple", -10);
        assertEquals("Quantity must be a positive number.", result);
        assertFalse(products.containsKey("Apple"));
    }

    @Test
    void testAddProductWithZeroQuantity() {
        String result = Main.addProduct("Mango", 0);
        assertEquals("Quantity must be a positive number.", result);
        assertFalse(products.containsKey("Mango"));
    }

    @Test
    void testAddProductThatAlreadyExistsWithSameQuantity() {
        products.put("Cheese", 5);
        assertTrue(products.containsKey("Cheese"));
        assertEquals(5, products.get("Cheese"));

        String result = Main.addProduct("Cheese", 5);
        assertEquals("Product already exists.", result);
        assertTrue(products.containsKey("Cheese"));
        assertEquals(5, products.get("Cheese"));
    }
    @Test
    void testAddProductThatAlreadyExistsWithDifferentQuantity() {
        products.put("Milk", 10);
        assertTrue(products.containsKey("Milk"));
        assertEquals(10, products.get("Milk"));

        String result = Main.addProduct("Milk", 50);
        assertEquals("Product already exists. Updating stock instead.", result);
        assertTrue(products.containsKey("Milk"));
        assertEquals(50, products.get("Milk"));
    }

    @Test
    void testCheckProductThatExists() {
        products.put("Milk", 20);
        assertTrue(products.containsKey("Milk"));
        assertEquals(20, products.get("Milk"));

        String result = Main.checkProduct("Milk");
        assertEquals("Milk is in stock: 20", result);
    }

    @Test
    void testCheckProductThatDoesNotExist() {
        String result = Main.checkProduct("Ice Cream");
        assertEquals("Product not found.", result);
    }

    @Test
    void updateProductThatExistsWithValidQuantity() {
        products.put("Bread", 20);
        assertTrue(products.containsKey("Bread"));
        assertEquals(20, products.get("Bread"));

        String result = Main.updateProduct("Bread", 25);
        assertEquals("Stock updated!", result);
        assertEquals(25, products.get("Bread"));
    }

    @Test
    void updateProductThatExistsWithInValidQuantity() {
        products.put("Butter", 10);
        assertTrue(products.containsKey("Butter"));
        assertEquals(10, products.get("Butter"));

        String result = Main.updateProduct("Butter", -2);
        assertEquals("Quantity must be a positive number.", result);
        assertEquals(10, products.get("Butter"));
    }

    @Test
    void updateProductThatExistsWithZeroQuantity() {
        products.put("Butter", 10);
        assertTrue(products.containsKey("Butter"));
        assertEquals(10, products.get("Butter"));

        String result = Main.updateProduct("Butter", 0);
        assertEquals("Quantity must be a positive number.", result);
        assertEquals(10, products.get("Butter"));
    }

    @Test
    void updateProductThatDoesNotExist() {
        String result = Main.updateProduct("Tofu", 20);
        assertEquals("Product not found.", result);
    }

    @Test
    void removeProductThatExists() {
        products.put("Eggs", 10);
        assertTrue(products.containsKey("Eggs"));

        String result = Main.removeProduct("Eggs");
        assertEquals("Product removed.", result);
        assertFalse(products.containsKey("Eggs"));
    }

    @Test
    void removeProductThatDoesNotExist() {
        String result = Main.removeProduct("Pizza");
        assertEquals("Product not found.", result);
    }
}