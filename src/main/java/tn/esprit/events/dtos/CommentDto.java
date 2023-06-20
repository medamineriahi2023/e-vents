package tn.esprit.events.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.events.entities.Comment;
import tn.esprit.events.utils.UserKcService;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
    private Long id;
    private UserDto userDto;

    private  String content;

    public static CommentDto entityToDto(Comment comment){
    return CommentDto.builder().content(comment.getContent()).id(comment.getId()).userDto(UserKcService.findById(comment.getUserId())).build();
    }

    public static Comment dtoToEntity(CommentDto commentDto){
        return Comment.builder().content(commentDto.getContent()).id(commentDto.getId()).userId(commentDto.userDto.getId()).build();
    }

    public static List<CommentDto> entitiesToDtos(List<Comment> comments){
        return comments.stream().map(CommentDto::entityToDto).collect(Collectors.toList());
    }

    public static List<Comment> dtosToEntities(List<CommentDto> commentsDtos){
        return commentsDtos.stream().map(CommentDto::dtoToEntity).collect(Collectors.toList());
    }
}
