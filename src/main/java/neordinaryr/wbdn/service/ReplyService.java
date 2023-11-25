package neordinaryr.wbdn.service;

import lombok.RequiredArgsConstructor;
import neordinaryr.wbdn.converter.ReplyConverter;
import neordinaryr.wbdn.domain.Comment;
import neordinaryr.wbdn.domain.Member;
import neordinaryr.wbdn.domain.Reply;
import neordinaryr.wbdn.domain.dto.request.ReplyRequestDto;
import neordinaryr.wbdn.global.apiPayload.ErrorCode;
import neordinaryr.wbdn.global.exception.BaseException;
import neordinaryr.wbdn.repository.ReplyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final CommentService commentService;

    @Transactional
    public Reply save(Long commentId, ReplyRequestDto.SaveReplyDto dto, Member member) {
        Reply reply = ReplyConverter.toReply(dto);
        reply.setMember(member);

        Comment comment = commentService.findById(commentId);
        reply.setComment(comment);

        return replyRepository.save(reply);
    }

    @Transactional
    public void delete(Long replyId, Member member) {
        Reply findReply = replyRepository.findById(replyId)
                .orElseThrow(() -> BaseException.of(ErrorCode.NO_SUCH_REPLY_ERROR));

        // 찾은 reply의 memberId와 받아온 member의 memberId가 같은지 검증
        if (findReply.getMember().getId().equals(member.getId())) {
            throw BaseException.of(ErrorCode.REPLY_MEMBER_MISMATCH_ERROR);
        }

        replyRepository.delete(findReply);
    }

    public Reply findById(long replyId) {
        return replyRepository.findById(replyId)
                .orElseThrow(() -> BaseException.of(ErrorCode.NO_SUCH_REPLY_ERROR));
    }

}
