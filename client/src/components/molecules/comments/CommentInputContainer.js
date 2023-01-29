import styled from 'styled-components';
import { useState } from 'react';
import Input from '../../atoms/Input';
import { OrangeButton } from '../../atoms/Buttons';
import { usePostComment } from '../../../hooks/useCommentAPI';

const Container = styled.div`
  display: flex;
  width: 100%;
  height: 60px;
  margin: 23px 0;
  justify-content: space-between;
  align-items: center;
`;

const CommentInputContainer = ({ id }) => {
  const [content, setContent] = useState('');

  const onChangeContent = e => {
    e.preventDefault();
    setContent(e.target.value);
  };

  const onClickCommentSubmit = async () => {
    if (content.length < 10) {
      alert('Minimum 10 characters.');
    } else {
      usePostComment(id, content);
    }
  };
  return (
    <Container>
      <Input value={content} onChange={onChangeContent} width="85%" height="50px" placeholder="댓글 달기" />
      <OrangeButton onClick={onClickCommentSubmit} width="60px" height="50px">
        Add
      </OrangeButton>
    </Container>
  );
};

export default CommentInputContainer;
