import styled from 'styled-components';

const Container = styled.div`
  display: flex;
  box-sizing: border-box;
  width: 100%;
  height: ${props => (props.height ? props.height : '116px')};
  align-items: center;
  justify-content: space-between;
  align-items: center;
  padding: 20px 60px;
  gap: 10px;
  border: 5px solid var(--gray-700);
  border-radius: 15px;
  box-shadow: var(--boxShadow-01) var(--gray-700);
`;

const TextContainer = styled.div`
  display: flex;
  flex-direction: column;
`;

/**
 * props 설명
 * headerTitle: Header 왼쪽 타이틀
 * asideHeader: Header 오른쪽 추가 컴포넌트
 * height: Header 외부 컨테이너의 높이
 */

const PageHeader = ({ headerTitle, asideHeader, height }) => {
  return (
    <Container height={height}>
      <TextContainer>
        <div style={{ fontSize: 'var(--display-s)', fontWeight: '700' }}>{headerTitle}</div>
      </TextContainer>
      {asideHeader || null}
    </Container>
  );
};

export default PageHeader;
