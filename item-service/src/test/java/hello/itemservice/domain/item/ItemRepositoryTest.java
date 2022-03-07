package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    public void save() throws Exception {
        //given
        final Item itemA = new Item("itemA", 10000, 10);
        //when
        final Item savedItem = itemRepository.save(itemA);
        //then
        final Item findItem = itemRepository.findById(itemA.getId());
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    public void findAll() throws Exception {
        //given
        final Item itemA = new Item("itemA", 10000, 10);
        final Item itemB = new Item("itemB", 20000, 20);

        itemRepository.save(itemA);
        itemRepository.save(itemB);
        //when
        final List<Item> result = itemRepository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(itemA, itemB);
    }

    @Test
    public void updateItem() throws Exception {
        //given
        final Item itemA = new Item("itemA", 10000, 10);
        final Item updateParam = new Item("itemB", 20000, 20);

        //when
        itemRepository.save(itemA);
        itemRepository.update(itemA.getId(), updateParam);

        final Item findItem = itemRepository.findById(itemA.getId());
        //then
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }
}