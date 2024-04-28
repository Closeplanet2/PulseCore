package com.pandapulsestudios.pulsecore.BookAPI.API;

import com.pandapulsestudios.pulsecore.BookAPI.Event.CustomBookOpenEvent;
import lombok.Setter;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class BookAPI {
    public static BookBuilder BookBuilder(String bookAuthor, String bookTitle){return new BookBuilder(bookAuthor, bookTitle);}
    public static class BookBuilder{
        private ItemStack book;
        private BookMeta bookMeta;

        public BookBuilder(String bookAuthor, String bookTitle){
            book = new ItemStack(Material.WRITTEN_BOOK);
            bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setAuthor(bookAuthor);
            bookMeta.setTitle(bookTitle);
        }

        public BookBuilder generation(BookMeta.Generation generation){
            this.bookMeta.setGeneration(generation);
            return this;
        }

        public BookBuilder bookPages(String... bookPages){
            this.bookMeta.addPage(bookPages);
            return this;
        }

        public BookBuilder bookPages(TextComponent... bookPages){
            this.bookMeta.spigot().addPage(bookPages);
            return this;
        }

        public ItemStack Open(Player... players){
            var itemStack = Build();
            for(var player : players){
                if(new CustomBookOpenEvent(player, itemStack).isCancelled()) continue;
                player.closeInventory();
                player.openBook(itemStack);
            }
            return itemStack;
        }

        public ItemStack Build() {
            book.setItemMeta(bookMeta);
            return book;
        }
    }
}
