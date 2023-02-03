import FadeLoader from 'react-spinners/FadeLoader';
import styled from 'styled-components';

const Loading = () => {
  return (
    <Container>
      <FadeLoader color="var(--gray-500)" height={15} margin={2} />
    </Container>
  );
};

export default Loading;

const Container = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 20vh;
`;
