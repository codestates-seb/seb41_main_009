import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import { CANCELPOSTCREATE } from '../../../constants/Messages';
import { BlackShadowButton, WhiteShadowButton } from '../../atoms/Buttons';

const Container = styled.div`
  display: flex;
  width: 100%;
  height: 50px;
  align-items: center;
  justify-content: flex-end;
`;

const PostCreateButtons = ({ submitNewPost }) => {
  const navigate = useNavigate();

  const cancelPostCreate = () => {
    if (window.confirm(CANCELPOSTCREATE)) navigate(-1);
  };

  return (
    <Container>
      <WhiteShadowButton margin="0 30px" onClick={cancelPostCreate}>
        Cancel
      </WhiteShadowButton>
      <BlackShadowButton onClick={submitNewPost}>Submit</BlackShadowButton>
    </Container>
  );
};

export default PostCreateButtons;
