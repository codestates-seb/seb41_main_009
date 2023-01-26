import styled from 'styled-components';
import { LabelListTitle, LabelMedium, ParagraphSmall } from '../../../styles/typo';

/**
 * 포스트 헤더 molecule
 * @param {string} title - 포스트 타이틀
 * @param {string} description - 포스트의 설명
 * @param {string} postCount - 포스트의 개수
 * @returns {JSX.Element} - 내부 태그들을 포함한 컴포넌트
 */
const SeriesTitle = ({ title, description = '디스크림션이 없음', postCount = '포스트 카운트가 없음' }) => {
  return (
    <Container>
      <Title>{title}</Title>
      <Count>
        총 Post <b>{postCount}</b>개
      </Count>
      <Desc>{description}</Desc>
    </Container>
  );
};

const Container = styled.div`
  display: flex;
  flex-direction: column;
  gap: 10px;
  color: white;
`;

const Title = styled.div`
  ${LabelListTitle};
`;

const Count = styled.div`
  ${LabelMedium};
`;

const Desc = styled.div`
  ${ParagraphSmall};
`;

export default SeriesTitle;
