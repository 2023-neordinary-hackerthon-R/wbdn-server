package neordinaryr.wbdn.service;

import java.util.List;
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
//    private final MemberService memberService;
//    private final PostService postService;

    @Transactional
    public Comment save(CommentRequestDto.SaveCommentDto dto, Long memberId) {
        Comment comment = CommentConverter.toComment(dto);

        // TODO: MemberService에서 Member를 가져오는 로직을 추가해야 함
        // Member member = memberService.findById(memberId);
        Member member = new Member();
        comment.setMember(member);

        // TODO: PostService에서 Post를 가져오는 로직을 추가해야 함
        // Post post = postService.findById(dto.getPostId());
        Post post = new Post();
        comment.setPost(post);

        return commentRepository.save(comment);
    }

    @Transactional
    public void delete(CommentRequestDto.DeleteCommentDto dto, Long memberId) {
        Long commentId = dto.getCommentId();
        Comment findComment = commentRepository.findById(commentId)
                .orElseThrow(() -> BaseException.of(ErrorCode.NO_SUCH_COMMENT_ERROR));
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
