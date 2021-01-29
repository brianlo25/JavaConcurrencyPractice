package concrurrency.chapter01;

import java.sql.ResultSet;

public interface RoeHandler<T> {
    T handle(ResultSet rs);
}
