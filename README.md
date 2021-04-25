# jpa-spring-boot-starter

## 介绍
基于spring-boot-starter-jpa 实现的允许自定义repository sql的组件
## 使用说明

### 1. 添加依赖

```xml
<dependency>
    <groupId>com.github.dzy5639313</groupId>
    <artifactId>jpa-spring-boot-starter</artifactId>
    <version>1.0.0-RELEASE</version>
</dependency>
```

### 2. 配置参数
```yaml
spring:
  datasource:
    url:
    username:
    password
```
> 参考jpa数据库设置
>
### 3. 代码中使用
```java
// 先开启功能注解, 并指定entity包路径和repository包路径, 支持多路径
@EnableCustomJPA(entityPackages = {"com.example.jpatest"}, repositoryPackages = {"com.example.jpatest"})
```
```java
/**
 * 定义model
 */
@Data
@Entity(name = "gameModel")
@Table(name = "lot_game")
public class GameModel {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "game_name")
    private String gameName;
    @Column(name = "game_code")
    private String gameCode;
}
```
```java
/**
 * 自定义repository接口
 */
public interface GameCustomRepository {
    GameModel getByCode(String gameCode);
}
```
```java
/**
 * 自定义repository接口实现, 自定义sql在这边
 */
public class GameRepositoryImpl implements GameCustomRepository {
    @PersistenceContext
    private EntityManager em;
    @Override
    public GameModel getByCode(String gameCode) {
        String sql = "select * from lot_game where game_code = :gameCode";

        List<GameModel> list = em.createNativeQuery(sql, GameModel.class).setParameter("gameCode", gameCode).getResultList();
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}
```
```java
/**
 * 正常依赖注入的repository, 可以使用jpa本身提供的find语法, 也可以使用自定义的方法
 */
@Repository
public interface GameRepository extends BaseRepository<GameModel, Long>, GameCustomRepository {
}
```