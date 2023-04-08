package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.domain.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@BatchSize(size = 1000)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    //공통 속성
    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //==비즈니스 로직==//
    /**
     * stock 증가
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }
    /**
     * stock 감소
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0) {throw new NotEnoughStockException("need more stock");}
        this.stockQuantity = restStock;
    }

    /**
     * 업데이트
     */
    public void update(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
