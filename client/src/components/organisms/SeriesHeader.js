import styled from 'styled-components';
import { useSearchParams } from 'react-router-dom';
import SeriesHeaderPostList from '../molecules/series/SeriesPostList';
import SeriesInfo from '../molecules/series/SeriesInfo';
import Category from '../atoms/Category';
import useSeriesStore from '../../store/seriesStore';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: inherit;
  height: fit-content;
  justify-content: center;
  align-items: center;
  backdrop-filter: ${props => (props.blur ? 'blur(30px) brightness(70%)' : 'none')};
  overflow: hidden;
  gap: 30px;
  padding-top: 50px;
  padding-bottom: 50px;
`;
const BackgroundWrapper = styled.div`
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

const CategoryContainer = styled.div`
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  justify-content: flex-start;
`;

const SeriesInfoContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 90%;
  justify-content: flex-start;
  align-items: flex-start;
  gap: 20px;
`;

const SeriesHeader = ({ series, seriesId }) => {
  const { isToggleBlur } = useSeriesStore();
  const [page] = useSearchParams('page');

  return (
    <BackgroundWrapper>
      <Container blur={isToggleBlur}>
        <SeriesInfoContainer>
          <CategoryContainer>
            <Category id={series.category} padding="10px 30px">
              {series.category}
            </Category>
          </CategoryContainer>
          <SeriesInfo series={series} />
        </SeriesInfoContainer>

        <SeriesHeaderPostList seriesId={seriesId} page={page} series={series} />
      </Container>
    </BackgroundWrapper>
  );
};

export default SeriesHeader;
