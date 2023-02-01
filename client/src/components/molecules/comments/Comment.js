import styled from 'styled-components';
import { useState } from 'react';
import useCommentAPI from '../../../hooks/useCommentAPI';
import { UserInfoSmall } from '../UserInfo';
import Input from '../../atoms/Input';

const Container = styled.div`
  display: flex;
  width: inherit;
  height: 36px;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
`;

const InfoContainer = styled.div`
  display: flex;
  width: 120px;
  align-items: center;
  justify-content: space-between;
`;

const CommentContainer = styled.div`
  width: 80%;
  padding: 0 24px;
  font-size: var(--paragraph-m);
`;

const CommentButton = styled.button`
  width: ${props => (props.width ? props.width : '51px')};
  height: ${props => (props.height ? props.height : '36px')};
  box-sizing: border-box;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #ffffff;
  border: none;
  font-size: var(--label-xs);
  color: black;

  &:hover {
    background-color: var(--gray-50);
    cursor: pointer;
  }
`;

const Comment = ({ basePath, comment }) => {
  const { content, id, writer } = comment;
  const [editMode, setEditMode] = useState(false);
  const [editContent, setEditContent] = useState(content);
  const { deleteComment, postComment } = useCommentAPI();

  const onClickEditComment = () => {
    setEditMode(!editMode);
  };

  const onClickDeleteComment = () => {
    deleteComment(basePath, id);
  };

  const onChangeContent = e => {
    e.preventDefault();
    setEditContent(e.target.value);
  };

  // CommentInputContainer 에 있는 submit 형태로 수정 해보기
  const onClickCommentSubmit = () => {
    setEditMode(!editMode);
    postComment(basePath, id, editContent);
  };

  return (
    <Container>
      <InfoContainer>
        <UserInfoSmall id={writer.id} name={writer.nickname} image={writer.profileImageUrl} />
      </InfoContainer>
      <CommentContainer>
        {!editMode ? (
          editContent
        ) : (
          <Input value={editContent} onChange={onChangeContent} width="85%" height="50px" placeholder="댓글 달기" />
        )}
      </CommentContainer>
      {!editMode ? (
        <InfoContainer>
          <CommentButton type="button" onClick={onClickEditComment}>
            Edit
          </CommentButton>
          <CommentButton type="button" onClick={onClickDeleteComment}>
            Delete
          </CommentButton>
        </InfoContainer>
      ) : (
        <InfoContainer>
          <CommentButton type="button" onClick={onClickCommentSubmit}>
            Cancel
          </CommentButton>
          <CommentButton type="button" onClick={onClickCommentSubmit}>
            Submit
          </CommentButton>
        </InfoContainer>
      )}
    </Container>
  );
};

export default Comment;
