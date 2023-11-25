package neordinaryr.wbdn.service;

import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import neordinaryr.wbdn.converter.CommentConverter;
import neordinaryr.wbdn.domain.Comment;
import neordinaryr.wbdn.domain.Member;
import neordinaryr.wbdn.domain.Post;
import neordinaryr.wbdn.domain.Reply;
import neordinaryr.wbdn.domain.dto.request.CommentRequestDto;
import neordinaryr.wbdn.global.apiPayload.ErrorCode;
import neordinaryr.wbdn.global.exception.BaseException;
import neordinaryr.wbdn.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
//    private final PostService postService;

    @Transactional
    public Comment save(Long postId, CommentRequestDto.SaveCommentDto dto, Member member) {
        Comment comment = CommentConverter.toComment(dto);
        comment.setMember(member);

        // TODO: Post post = postService.findById(postId)로 대체
        Post post = new Post();
        comment.setPost(post);

        return commentRepository.save(comment);
    }

    @Transactional
    public void delete(Long commentId, Member member) {
        Comment findComment = commentRepository.findById(commentId)
                .orElseThrow(() -> BaseException.of(ErrorCode.NO_SUCH_COMMENT_ERROR));

        // 찾은 comment의 memberId와 받아온 member의 memberId가 같은지 검증
        if (findComment.getMember().equals(member)) {
            throw BaseException.of(ErrorCode.COMMENT_MEMBER_MISMATCH_ERROR);
        }

        commentRepository.delete(findComment);
    }

    public Comment findById(long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> BaseException.of(ErrorCode.NO_SUCH_COMMENT_ERROR));
    }

    public List<Reply> findRepliesById(long commentId) {
        Comment findComment = findById(commentId);
        return findComment.getReplies();
    }

}
