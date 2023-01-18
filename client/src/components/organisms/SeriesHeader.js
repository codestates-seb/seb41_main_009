import styled from 'styled-components';
import SeriesHeaderPostList from '../molecules/series/SeriesHeaderPostList';
import SeriesInfo from '../molecules/series/SeriesInfo';
import { AcrylicBase } from '../atoms/AcrylicBase';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: inherit;
  height: inherit;
  justify-content: center;
  align-items: center;
  background: url(https://unsplash.it/1920/1080/?random) rgba(45, 45, 45, 0.44);
  overflow: hidden;
  border-radius: 30px;
`;

const SeriesHeader = () => {
  return (
    <Container>
      <AcrylicBase flexDirection="column">
        <SeriesInfo
          userName="userName"
          userImage="https://unsplash.it/1920/1080/?random"
          title="seriesTitle"
          desc="descrption"
        />
        <SeriesHeaderPostList />
      </AcrylicBase>
    </Container>
  );
};

export default SeriesHeader;
