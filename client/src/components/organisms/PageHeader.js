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
 * @param headerTitle: Header 왼쪽 하단 타이틀
 * @param headerSubTitle: Header 왼쪽 상단 타이틀
 * @param asideHeader: Header 오른쪽 추가 컴포넌트
 * @param height: Header 외부 컨테이너의 높이
 */

const PageHeader = ({ headerTitle, headerSubTitle, asideHeader, height }) => {
  return (
    <Container height={height}>
      <TextContainer>
        <div style={{ fontSize: 'var(--label-l)', fontWeight: '500' }}>{headerSubTitle || 'Subtitle'}</div>
        <div style={{ fontSize: 'var(--display-s)', fontWeight: '700' }}>{headerTitle || 'TITLE'}</div>
      </TextContainer>
      {asideHeader || null}
    </Container>
  );
};

export default PageHeader;
