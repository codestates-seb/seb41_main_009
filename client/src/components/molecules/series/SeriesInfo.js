import styled from 'styled-components';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import SeriesTitle from './SeriesTitle';
import { UserInfoSmall } from '../UserInfo';
import { LabelSmall, LabelXSmall } from '../../../styles/typo';
import { ClearBlurButton } from '../../atoms/Buttons';
import useSeriesStore from '../../../store/seriesStore';
import useAuthStore from '../../../store/useAuthStore';

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
  const navigate = useNavigate();

  const { currentUserId } = useAuthStore(state => state);

  const { setBlur } = useSeriesStore();
  const { title, content, views, createdAt, modifiedAt, member } = series;
  // TODO: 페이지 구현시 createAt, modifiedAt 은 Date 타입으로 받아 컴포넌트 내에서 변환

  // console.log(series, 'series in SeriesInfo');
  const handleClickBlurButton = () => {
    setBlur();
  };

  const handleClickRemove = () => {
    const url = `series/${series?.id}`;

    axios
      .delete(url)
      .then(res => {
        console.log(res);
        navigate(`/series/`);
      })
      .catch(err => console.log(err));
  };
  return (
    <Container>
      <SeriesTitle title={title} description={content} postCount={views} />
      <SeriesInfoContainer>
        <SeriesInfoList>
          <ClearBlurButton handleClick={handleClickBlurButton} />
          {/* series.member.id 하면 오류남 */}
          <UserInfoSmall
            id={member?.id}
            name={member?.nickname}
            image={member?.profileUrl}
            typo={LabelXSmall}
            size="24px"
          />
        </SeriesInfoList>
        <IconList>
          <CreatedAtText>createAt {new Date().toDateString(createdAt)}</CreatedAtText>
          <CreatedAtText>modifiedAt {new Date().toDateString(modifiedAt)}</CreatedAtText>
          {currentUserId === member?.id ? (
            <>
              <Button type="button" to={`/series/${series.id}/new`}>
                Edit
              </Button>
              <Button type="button" onClick={handleClickRemove}>
                Delete
              </Button>
            </>
          ) : (
            ''
          )}
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

const Button = styled(Link)`
  width: ${props => (props.width ? props.width : '51px')};
  height: ${props => (props.height ? props.height : '36px')};
  text-decoration: none;
  box-sizing: border-box;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: none;
  border: none;
  color: var(--gray-50);
  ${LabelSmall}
  &:hover {
    background-color: rgba(207, 207, 207, 20%);
    cursor: pointer;
  }
`;
export default SeriesInfo;
