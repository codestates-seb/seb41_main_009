import styled from 'styled-components';
import { useRef, useState } from 'react';
import Box from '../../atoms/Box';
import useShowcaseStore from '../../../store/showcaseStore';
import { UserInfoSmall } from '../../molecules/UserInfo';
import { ParagraphMedium } from '../../../styles/typo';
import Nickname from '../../atoms/Nickname';
import ImageCard from '../../molecules/ImageCard';
import Input from '../../atoms/Input';
import useAuthStore from '../../../store/useAuthStore';

/**
 * 썸네일을 포함한 메인페이지용 쇼케이스 박스 organisms
 * @param {string} thumnail - 썸네일 URL
 * @param {string|number} tagId - 카테고리의 아이디
 * @param {string} tagName - 카테고리의 이름
 * @param {string} userImg - 글쓴이 프로필 URL
 * @param {string} userName - 글쓴이 닉네임
 * @param {string} summary - 본문
 * @param {string} commentUserName - 첫댓글 작성자 닉네임
 * @param {string} commentContent - 첫댓글작성 내용
 * @returns {JSX.Element} - 메인페이지 쇼케이스박스 컴포넌트
 */
const Showcasebox = ({
  id,
  thumnail,
  tagName,
  userImg,
  userId,
  userName,
  summary,
  commentUserName,
  commentContent,
  handle,
}) => {
  const commentRef = useRef(null);
  const [commentInput, setCommentInput] = useState('');
  const { postComment } = useShowcaseStore();
  const { currentUserId } = useAuthStore();

  const handleOnKeyEnter = e => {
    if (e.key === 'Enter') {
      e.preventDefault();
      postComment(id, commentInput, currentUserId, () => {
        setCommentInput('');
        e.target.value = '';
      });
    }
  };

  return (
    <Container>
      <ImageCard thumnail={thumnail} id={tagName} name={tagName} handle={handle} />
      <Box>
        <UserInfoSmall id={userId} name={userName} image={userImg} />
        <ContentSummary>{summary}</ContentSummary>
      </Box>
      <Box>
        <CommentLine>
          <Nickname name={commentUserName} typo={ParagraphMedium} color="var(--gray-500)" />
          <CommentSummary>{commentContent}</CommentSummary>
        </CommentLine>
        <CommentInput
          placeholder="댓글 작성"
          ref={commentRef}
          onKeyDown={e => handleOnKeyEnter(e)}
          onChange={e => setCommentInput(e.target.value)}
        />
      </Box>
    </Container>
  );
};

const Container = styled.div`
  width: 100%;
  margin-bottom: 30px;
  transition: all 0.3s ease-out;

  &:hover {
    transform: translateY(-10px);
    box-shadow: 1px 1px 10px #aaa;
  }

  & > * {
    margin-bottom: 10px;
  }
`;

const ContentSummary = styled.div`
  margin-top: 19px;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
  overflow: hidden;
`;

const CommentLine = styled.div`
  display: flex;
  gap: 15px;
`;

const CommentSummary = styled.div`
  ${ParagraphMedium};
  width: 200px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
`;

const CommentInput = styled(Input)`
  margin-top: 11px;
  background-color: var(--gray-100);
  color: var(--gray-900);
  border-radius: 15px;
`;

export default Showcasebox;
