import styled from 'styled-components';
import SeriesTitle from './SeriesTitle';
import { UserInfoSmall } from '../UserInfo';
import { LabelSmall, LabelXSmall } from '../../../styles/typo';
import { ClearBlurButton } from '../../atoms/Buttons';
import useSeriesStore from '../../../store/seriesStore';

/**
 * 포스트 상세페이지와 검색결과에 활용할수 있는 포스트헤더 organism
 * @param {string|number} id - 작성자의 id
 * @param {string} userName - 작성자의 닉네임
 * @param {string} userImage - 작성자의 이미지 URL
 * @param {string} title - 포스트의 타이틀
 * @param {string} desc - 포스트의 설명
 * @param {string|number} postCount - 시리즈내에 포함된 포스트의 개수
 * @param {string} createdAt - 생성날짜
 * @param {string} modifiedAt - 수정날짜
 * @param {boolean} border - true 인경우 default 사용
 * @param {string} width - 컨테이너 넓이 고정설정
 * @param {string} height - 컨테이너 높이 고정설정
 * @returns {JSX.Element} -
 */
const SeriesInfo = ({ series }) => {
  const { setBlur } = useSeriesStore();
  const { title, content, views, createdAt, modifiedAt } = series;
  // TODO: 페이지 구현시 createAt, modifiedAt 은 Date 타입으로 받아 컴포넌트 내에서 변환

  const handleClickBlurButton = () => {
    setBlur();
  };
  return (
    <Container>
      <SeriesTitle title={title} description={content} postCount={views} />
      <SeriesInfoContainer>
        <SeriesInfoList>
          <ClearBlurButton handleClick={handleClickBlurButton} />
          {/* series.member.id 하면 오류남 */}
          <UserInfoSmall typo={LabelXSmall} size="24px" />
        </SeriesInfoList>
        <IconList>
          <CreatedAtText>createAt {new Date().toDateString(createdAt)}</CreatedAtText>
          <CreatedAtText>modifiedAt {new Date().toDateString(modifiedAt)}</CreatedAtText>
          <Icon>Edit</Icon>
          <Icon>Delete</Icon>
        </IconList>
      </SeriesInfoContainer>
    </Container>
  );
};

const CreatedAtText = styled.div`
  color: var(--gray-400);
`;

const Container = styled.div`
  display: flex;
  padding: 20px 40px;
  width: ${props => props.width || '91.5%'};
  border: ${props => (props.border ? '2px solid black' : 'none')};
  height: ${props => props.height || '104px'};
  justify-content: space-between;

  border-radius: 30px;
  background-color: rgba(84, 84, 84, 0.5);
  color: white;
`;

const SeriesInfoContainer = styled.div`
  ${LabelXSmall};
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  width: fit-content;
`;

const SeriesInfoList = styled.div`
  display: flex;
  justify-content: flex-end;
  align-items: center;
  height: 40px;
  gap: 10px;
`;

const IconList = styled.div`
  display: flex;
  align-items: center;
  justify-content: flex-end;
  height: 36px;
  gap: 10px;
`;
const Icon = styled.div`
  ${LabelSmall}
`;
export default SeriesInfo;
