import styled from 'styled-components';
import { BlueShadowButton } from '../../atoms/Buttons';

const UserTitle = () => {
  return (
    <Container>
      <Desc> User Description </Desc>
      <BlueShadowButton> Edit </BlueShadowButton>
    </Container>
  );
};

const Container = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: green;
  height: 227px;

  & > * {
    margin: 30px;
  }
`;

const Desc = styled.div`
  font-size: 18px;
`;

export default UserTitle;
