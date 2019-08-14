package ru.shangareev.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shangareev.entities.Product;
import ru.shangareev.repositories.ProductRepository;
import ru.shangareev.entities.User;
import ru.shangareev.repositories.UserRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener
public class ContextListener implements ServletContextListener {

    private Logger logger = LoggerFactory.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        logger.info("Context initialization: " + context.getContextPath());

        String jdbcConnectionString = context.getInitParameter("jdbcConnectionString");
        String username = context.getInitParameter("username");
        String password = context.getInitParameter("password");

        if (isNotNullOrEmpty(jdbcConnectionString) || isNotNullOrEmpty(username)) {
            logger.error("Connection string and DB username must be specified");
            return;
        }

        try {
            Connection connection = DriverManager.getConnection(jdbcConnectionString, username, password);
            context.setAttribute("jdbcConnection", connection);
            UserRepository userRepository = new UserRepository(connection);
            context.setAttribute("userRepository", userRepository);

            if (userRepository.getAllUsers().size() == 0) {
                userRepository.insert(new User(-1, "first_user", "ppp"));
                userRepository.insert(new User(-1, "second_user", "ppp"));
            }


            ProductRepository productRepository = new ProductRepository(connection);
            context.setAttribute("productRepository", productRepository);

            if (productRepository.getAllProducts().size() == 0) {
                productRepository.insert(new Product(1, "Телевизор Sony KD-43XF7096", "Телевизор Sony KD-XF7096 использует " +
                        "технологию X-Reality Pro. Его графический процессор не просто подавляет шумы. Он сравнивает каждый кадр " +
                        "с «эталоном» из предустановленной базы данных, корректируя изображение. Результатом становится " +
                        "исключительно чёткая картинка с высокой резкостью, плавными линиями и яркими насыщенными цветами. ", 45990));

                productRepository.insert(new Product(1, "Телевизор LG 50UK6750", "Телевизор LG 50UK6750PLD оснащён LED-экраном с " +
                        "разрешением 3840х2160 пикселей и позволяет воспроизводить фильмы в самом высоком качестве и транслировать " +
                        "цифровой телесигнал в формате Ultra HD. Дисплей 50 дюймов подойдёт и для того, чтобы поболеть за любимую футбольную " +
                        "команду в компании друзей, и для уютного домашнего киносеанса в спальне. Стандартное крепление VESA (в комплект не входит) " +
                        "позволит разместить телевизионную панель на стене прямо напротив кровати.", 42990));

                productRepository.insert(new Product(1, "Телевизор Samsung UE50NU7097U", "Увидьте мир таким, какой он есть! " +
                        "Телевизор Samsung Series7 с поддержкой HDR и реалистичной цветопередачей порадует вас яркой и детализированной картинкой, " +
                        "что бы вы ни смотрели – документальный фильм, фантастический блокбастер или видео с семейного праздника.", 37990));

                productRepository.insert(new Product(1, "Монитор игровой AOC G2590FX", "Даже самая мощная сборка не гарантирует " +
                        "красивую картинку в современных ААА-играх. Чтобы в полной мере насладиться виртуальными мирами, потребуется помощь " +
                        "качественного монитора, который пригодится в реактивных боевиках и размеренных стратегиях.", 14990));

                productRepository.insert(new Product(1, "Монитор Samsung C24F390FHI", "Хотите увидеть реалистичное изображение? Тогда вам " +
                        "обязательно нужен монитор Samsung серии CF390, изготовленный на основе изогнутой матрицы.\n", 8990));

                productRepository.insert(new Product(1, "Монитор игровой Samsung C27JG50QQI", "27-дюймовый игровой монитор Samsung C27JG50QQI позволит" +
                        " полностью погрузиться в происходящее на экране. Эффект присутствия усиливают изогнутая форма дисплея и " +
                        "разрешение WQHD (2560x1440): плотность пикселей в 1,7 раз превышает таковую у моделей Full HD. Результат –" +
                        " высочайшая чёткость мельчайших деталей.", 22990));

            }
        } catch (SQLException e) {
            logger.error("", e);
        }
    }

    private boolean isNotNullOrEmpty(String str) {
        return str != null && str.isEmpty();
    }
}


