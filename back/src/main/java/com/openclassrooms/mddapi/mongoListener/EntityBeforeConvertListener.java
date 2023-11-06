package com.openclassrooms.mddapi.mongoListener;

import com.openclassrooms.mddapi.entity.Post;
import com.openclassrooms.mddapi.entity.Comment;
import com.openclassrooms.mddapi.entity.Topic;
import com.openclassrooms.mddapi.entity.User;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Date;

@Component
public class EntityBeforeConvertListener<T> extends AbstractMongoEventListener<T> {
    @Override
    public void onBeforeConvert(BeforeConvertEvent<T> event) {
        T entity = event.getSource();
        Date currentDate = new Date();
        if (entity instanceof Comment){
            updateDateOfComment((Comment) entity, currentDate);
        }
        else if (entity instanceof Post){
            updateDateOfArticle((Post) entity, currentDate);
            initPostList((Post) entity);
        }
        else if (entity instanceof User){
            updateDateOfUser((User) entity, currentDate);
            initUsertList((User) entity);
        }
        else if (entity instanceof Topic){
            updateDateOfTheme((Topic) entity, currentDate);
        }
    }

    private void initPostList(Post post) {
        if (post.getIdComment() == null)
            post.setIdComment(new ArrayList<>());
    }

    private void initUsertList(User user) {
        if (user.getIdTopic() == null)
            user.setIdTopic(new ArrayList<>());
        if  (user.getIdComment() == null)
            user.setIdComment(new ArrayList<>());
        if (user.getIdPost() == null)
            user.setIdPost(new ArrayList<>());
    }

    private void updateDateOfComment(Comment comment, Date currentDate) {
        if (comment.getCreated_at() == null) {
            comment.setCreated_at(currentDate);
        }
        comment.setUpdated_at(currentDate);
    }

    private void updateDateOfArticle(Post post, Date currentDate) {
        if (post.getCreated_at() == null) {
            post.setCreated_at(currentDate);
        }
        post.setUpdated_at(currentDate);
    }
    private void updateDateOfUser(User user, Date currentDate) {
        if (user.getCreated_at() == null) {
            user.setCreated_at(currentDate);
        }
        user.setUpdated_at(currentDate);
    }

    private void updateDateOfTheme(Topic topic, Date currentDate) {
        if (topic.getCreated_at() == null) {
            topic.setCreated_at(currentDate);
        }
        topic.setUpdated_at(currentDate);
    }
}
