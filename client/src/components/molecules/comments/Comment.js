import styled from 'styled-components';
import { useState } from 'react';
import { useDeleteComment, usePatchComment } from '../../../hooks/useCommentAPI';
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

const Comment = ({ comment }) => {
  const { content, id } = comment;
  const [editMode, setEditMode] = useState(false);

  const [editContent, setEditContent] = useState(content);

  const onClickEditAnswers = () => {
    setEditMode(!editMode);
  };

  const onClickDeleteAnswers = () => {
    useDeleteComment(id);
  };

  const onChangeContent = e => {
    e.preventDefault();
    setEditContent(e.target.value);
  };

  const onClickCommentSubmit = async () => {
    if (content.length < 10) {
      alert('Minimum 10 characters.');
    } else {
      setEditMode(!editMode);
      usePatchComment(id, editContent);
    }
  };

  return (
    <Container>
      <InfoContainer>
        <UserInfoSmall id={comment.writer.id} name={comment.writer.nickname} image={comment.writer.profileImageUrl} />
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
          <CommentButton type="button" onClick={onClickEditAnswers}>
            Edit
          </CommentButton>
          <CommentButton type="button" onClick={onClickDeleteAnswers}>
            Delete
          </CommentButton>
        </InfoContainer>
      ) : (
        <InfoContainer>
          <CommentButton type="button" onClick={onClickCommentSubmit}>
            Submit
          </CommentButton>
        </InfoContainer>
      )}
    </Container>
  );
};

export default Comment;
