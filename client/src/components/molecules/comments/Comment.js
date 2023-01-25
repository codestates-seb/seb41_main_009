import styled from 'styled-components';
import { UserInfoSmall } from '../UserInfo';

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

const Comment = ({ id, name, image, content, handleEdit, handleDelete }) => {
  return (
    <Container>
      <InfoContainer>
        <UserInfoSmall id={id} name={name} image={image} />
      </InfoContainer>
      <CommentContainer>{content}</CommentContainer>
      <InfoContainer>
        <CommentButton type="button" onClick={handleEdit}>
          Edit
        </CommentButton>
        <CommentButton type="button" onClick={handleDelete}>
          Delete
        </CommentButton>
      </InfoContainer>
    </Container>
  );
};

export default Comment;
