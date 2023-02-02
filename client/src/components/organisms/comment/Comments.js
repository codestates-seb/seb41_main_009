import { useState, useEffect } from 'react';

import styled from 'styled-components';

import useCommentAPI from '../../../hooks/useCommentAPI';
import CommentContentsContainer from '../../molecules/comments/CommentContentsContainer';
import CommentHeader from '../../molecules/comments/CommentHeader';
import CommentInputContainer from '../../molecules/comments/CommentInputContainer';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  box-sizing: border-box;
  padding: 22px 92px;
  width: 100%;
`;

const Comments = ({ basePath, id }) => {
  const { comments, commentCount, totalPages, getComment } = useCommentAPI();

  const [nowComments, setNowComments] = useState({});
  const [nowCommentCount, setNowCommentCount] = useState(0);
  const [nowtotalPages, setNowtotalPages] = useState(0);

  useEffect(() => {
    getComment(basePath, id, {
      page: 1,
      size: 5,
    });
    setNowComments(comments);
    setNowCommentCount(commentCount);
    setNowtotalPages(totalPages);
  }, [id]);

  console.log(basePath, id, 'basePath ,id in Comments');

  // console.log(comments, 'Commentsin Comments');
  return (
    <Container>
      <CommentHeader commentsCount={nowCommentCount} />
      {/* postId -댓글을 제출 할때 어떤 POST에 속해있는지 알려주기 위함 */}
      <CommentInputContainer basePath={basePath} postId={id} />
      <CommentContentsContainer comments={nowComments} basePath={basePath} totalPages={nowtotalPages} />
    </Container>
  );
};

export default Comments;
