import styled from 'styled-components';
import SeriesHeaderPostList from '../molecules/series/SeriesPostList';
import SeriesInfo from '../molecules/series/SeriesInfo';
import { AcrylicBase } from '../atoms/AcrylicBase';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: inherit;
  height: fit-content;
  justify-content: center;
  align-items: center;
  background: url(https://unsplash.it/1920/1080/?random) rgba(45, 45, 45, 0.44);
  overflow: hidden;
  border-radius: 30px;
`;

const SeriesHeader = ({ series }) => {
  console.log('series', series);
  return (
    <Container>
      <AcrylicBase flexDirection="column">
        <SeriesInfo
          title={series.title}
          userId={series.member.id}
          userName={series.member.nickname}
          userImage={series.member.profileUrl}
          createdAt={series.createdAt}
          modifiedAt={series.modifiedAt}
        />
        {/* Series Page의 갯수를 알 수있어야함 page 갯수 api response값 필요 */}
        <SeriesHeaderPostList seriesId={series.id} page={series.id} />
      </AcrylicBase>
    </Container>
  );
};

export default SeriesHeader;
