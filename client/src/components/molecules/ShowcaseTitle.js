import styled from 'styled-components';
import { DisplaySmall } from '../../styles/typo';
import { BlueShadowButton } from '../atoms/Buttons';

/**
 * 포스트 헤더 molecule
 * @param {string} title - 포스트 타이틀
 * @param {function} handleClick - 포스트의 설명
 * @param {string} postCount - 포스트의 개수
 * @returns {JSX.Element} - 내부 태그들을 포함한 컴포넌트
 */
const ShowcaseTitle = ({ handleClick }) => {
  return (
    <Container>
      <Title>Showcase</Title>
      <BlueShadowButton onClick={handleClick}>Create</BlueShadowButton>
    </Container>
  );
};

const Container = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  box-sizing: border-box;
  padding: 12px 30px;
  border: 5px solid var(--gray-900);
  border-radius: 15px;
  color: var(--gray-900);
  width: 100%;

  & > a {
    margin-right: 30px;
  }
`;

const Title = styled.div`
  ${DisplaySmall};
  letter-spacing: -1px;
  margin-left: 30px;
`;

export default ShowcaseTitle;
