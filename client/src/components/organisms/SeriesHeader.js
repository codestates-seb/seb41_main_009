import styled from 'styled-components';
import SeriesInfoContainer from '../molecules/SeriesInfoContainer';
import SeriesPostListContainer from '../molecules/SeriesPostListContainer';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: inherit;
  height: 548px;
  justify-content: center;
  align-items: center;
  background-color: ghostwhite;
`;

const SeriesHeader = () => {
  return (
    <Container>
      <SeriesInfoContainer />
      <SeriesPostListContainer />
    </Container>
  );
};

export default SeriesHeader;
