package com.openclassrooms.mddapi.mongoListener;

import com.openclassrooms.mddapi.entity.Article;
import com.openclassrooms.mddapi.entity.Comment;
import com.openclassrooms.mddapi.entity.Theme;
import com.openclassrooms.mddapi.entity.User;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class EntityBeforeConvertListener<T> extends AbstractMongoEventListener<T> {
    @Override
    public void onBeforeConvert(BeforeConvertEvent<T> event) {
        T entity = event.getSource();
        Date currentDate = new Date();

        if (entity == null)
            return;
        if (entity instanceof Comment){
            updateDateOfComment((Comment) entity, currentDate);
        }
        else if (entity instanceof Article){
            updateDateOfArticle((Article) entity, currentDate);
        }
        else if (entity instanceof User){
            updateDateOfUser((User) entity, currentDate);
        }
        else if (entity instanceof Theme){
            updateDateOfTheme((Theme) entity, currentDate);
        }

    }

    private void updateDateOfComment(Comment comment, Date currentDate) {
        if (comment.getCreated_at() == null) {
            comment.setCreated_at(currentDate);
        }
        comment.setUpdated_at(currentDate);
    }

    private void updateDateOfArticle(Article article, Date currentDate) {
        if (article.getCreated_at() == null) {
            article.setCreated_at(currentDate);
        }
        article.setUpdated_at(currentDate);
    }
    private void updateDateOfUser(User user, Date currentDate) {
        if (user.getCreated_at() == null) {
            user.setCreated_at(currentDate);
        }
        user.setUpdated_at(currentDate);
    }

    private void updateDateOfTheme(Theme theme, Date currentDate) {
        if (theme.getCreated_at() == null) {
            theme.setCreated_at(currentDate);
        }
        theme.setUpdated_at(currentDate);
    }

}
